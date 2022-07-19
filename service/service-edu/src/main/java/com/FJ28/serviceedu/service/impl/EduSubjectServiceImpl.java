package com.FJ28.serviceedu.service.impl;

import com.FJ28.servicebase.exceptionhandler.FJ28Exception;
import com.FJ28.serviceedu.entity.EduSubject;
import com.FJ28.serviceedu.entity.excel.SubjectData;
import com.FJ28.serviceedu.entity.subject.OneSubject;
import com.FJ28.serviceedu.entity.subject.TwoSubject;
import com.FJ28.serviceedu.listener.SubjectExcelListener;
import com.FJ28.serviceedu.mapper.EduSubjectMapper;
import com.FJ28.serviceedu.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author FJ28
 * @since 2022-07-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 文件输入
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FJ28Exception(20002, "添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> nestedList() {

        ArrayList<OneSubject> subjectArrayList = new ArrayList<>();

        // First level data record
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        // Second level data record
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //创建一级类别vo对象
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject, oneSubject);
            subjectArrayList.add(oneSubject);

            //填充二级分类vo数据
            ArrayList<TwoSubject> twoSubjectArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if (subject.getId().equals(subSubject.getParentId())) {

                    TwoSubject subjectVo = new TwoSubject();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    twoSubjectArrayList.add(subjectVo);
                }
            }
            oneSubject.setChildren(twoSubjectArrayList);
        }
        return subjectArrayList;
    }
}
