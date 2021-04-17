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
package me.zhengjie.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.domain.MntTask;
import me.zhengjie.service.MntTaskService;
import me.zhengjie.service.dto.MntTaskQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author abs
* @date 2021-04-13
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "task管理")
@RequestMapping("/api/mntTask")
public class MntTaskController {

    private final MntTaskService mntTaskService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('mntTask:list')")
    public void download(HttpServletResponse response, MntTaskQueryCriteria criteria) throws IOException {
        mntTaskService.download(mntTaskService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询task")
    @ApiOperation("查询task")
    @PreAuthorize("@el.check('mntTask:list')")
    public ResponseEntity<Object> query(MntTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(mntTaskService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增task")
    @ApiOperation("新增task")
    @PreAuthorize("@el.check('mntTask:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody MntTask resources){
        return new ResponseEntity<>(mntTaskService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改task")
    @ApiOperation("修改task")
    @PreAuthorize("@el.check('mntTask:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody MntTask resources){
        mntTaskService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除task")
    @ApiOperation("删除task")
    @PreAuthorize("@el.check('mntTask:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        mntTaskService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}