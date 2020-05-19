package com.lh.nailweb.controller.fish;

import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.entity.BaseMsg;
import com.lh.nailweb.entity.fish.Fish;
import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.service.fish.IFishService;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.LoginUtils;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.vo.fish.FishCreateVO;
import com.lh.nailweb.vo.fish.FishEditVO;
import com.lh.nailweb.vo.fish.FishVO;
import com.lh.nailweb.vo.page.fish.FishPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2020/4/30
 * @description:
 */
@Api(description = "鱼相关接口")
@RestController
@RequestMapping(value = "/fish")
public class FishController {
    @Autowired
    private IFishService fishService;

    @Autowired
    private LoginUtils loginUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "获取鱼类列表", notes = "获取鱼类列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "fishVO", value = "筛选鱼类VO", required = true)})
    @GetMapping("/list")
    public BaseMsg getFishList(HttpServletRequest request, @ModelAttribute FishVO fishVO) {
        User user = loginUtils.getCurrentUser(request, jwtTokenUtil.getHeader());
        List<Fish> list = fishService.getFishList(fishVO, user.getId());
        return MsgUtils.success(list);
    }

    @ApiOperation(value = "获取鱼类分页列表", notes = "获取鱼类分页列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "筛选鱼类Page", required = true)})
    @GetMapping("/page")
    public BaseMsg getFishPage(HttpServletRequest request, @ModelAttribute FishPage page) {
        User user = loginUtils.getCurrentUser(request, jwtTokenUtil.getHeader());
        page = fishService.getFishPage(page, user.getId());
        return MsgUtils.success(page);
    }

    @ApiOperation(value = "新增鱼类", notes = "新增鱼类")
    @PostMapping("/create")
    public BaseMsg createFish(@RequestBody FishCreateVO fishCreateVO) {
        if (StringUtils.isBlank(fishCreateVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "名称为空！");
        }
        fishService.createFish(fishCreateVO);
        return MsgUtils.success();
    }

    @ApiOperation(value = "修改鱼类", notes = "修改鱼类")
    @PutMapping("/edit")
    public BaseMsg editFish(@RequestBody FishEditVO fishEditVO) {
        if (StringUtils.isBlank(fishEditVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "名称为空！");
        }
        fishService.editFish(fishEditVO);
        return MsgUtils.success();
    }

    @ApiOperation(value = "删除鱼类", notes = "删除鱼类")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "鱼类id")})
    @DeleteMapping("/{id}")
    public BaseMsg deleteFish(@PathVariable("id") long id) {
        fishService.deleteFish(id);
        return MsgUtils.success();
    }
}
