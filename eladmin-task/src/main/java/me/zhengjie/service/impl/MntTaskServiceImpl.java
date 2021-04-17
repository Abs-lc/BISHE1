/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.service.impl;

import me.zhengjie.domain.MntTask;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.MntTaskRepository;
import me.zhengjie.service.MntTaskService;
import me.zhengjie.service.dto.MntTaskDto;
import me.zhengjie.service.dto.MntTaskQueryCriteria;
import me.zhengjie.service.mapstruct.MntTaskMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author abs
* @date 2021-04-13
**/
@Service
@RequiredArgsConstructor
public class MntTaskServiceImpl implements MntTaskService {

    private final MntTaskRepository mntTaskRepository;
    private final MntTaskMapper mntTaskMapper;

    @Override
    public Map<String,Object> queryAll(MntTaskQueryCriteria criteria, Pageable pageable){
        Page<MntTask> page = mntTaskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(mntTaskMapper::toDto));
    }

    @Override
    public List<MntTaskDto> queryAll(MntTaskQueryCriteria criteria){
        return mntTaskMapper.toDto(mntTaskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public MntTaskDto findById(Integer taskId) {
        MntTask mntTask = mntTaskRepository.findById(taskId).orElseGet(MntTask::new);
        ValidationUtil.isNull(mntTask.getTaskId(),"MntTask","taskId",taskId);
        return mntTaskMapper.toDto(mntTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MntTaskDto create(MntTask resources) {
        return mntTaskMapper.toDto(mntTaskRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MntTask resources) {
        MntTask mntTask = mntTaskRepository.findById(resources.getTaskId()).orElseGet(MntTask::new);
        ValidationUtil.isNull( mntTask.getTaskId(),"MntTask","id",resources.getTaskId());
        mntTask.copy(resources);
        mntTaskRepository.save(mntTask);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer taskId : ids) {
            mntTaskRepository.deleteById(taskId);
        }
    }

    @Override
    public void download(List<MntTaskDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MntTaskDto mntTask : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" name",  mntTask.getName());
            map.put("包含的服务数", mntTask.getServiceNum());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}