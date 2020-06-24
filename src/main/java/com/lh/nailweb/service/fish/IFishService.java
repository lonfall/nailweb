package com.lh.nailweb.service.fish;

import com.lh.nailweb.entity.fish.Fish;
import com.lh.nailweb.vo.fish.FishCreateVO;
import com.lh.nailweb.vo.fish.FishEditVO;
import com.lh.nailweb.vo.fish.FishListVO;
import com.lh.nailweb.vo.fish.FishVO;
import com.lh.nailweb.vo.page.fish.FishPage;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description:
 */
public interface IFishService {
    /**
     * 获取鱼类列表
     *
     * @param fishVO
     * @return
     */
    List<FishListVO> getFishList(FishVO fishVO);

    /**
     * 获取鱼类列表（登录后）
     *
     * @param fishVO
     * @param userId
     * @return
     */
    List<FishListVO> getFishListById(FishVO fishVO, long userId);

    /**
     * 获取鱼类分页列表
     *
     * @param page
     * @param userId
     * @return
     */
    FishPage getFishPage(FishPage page, long userId);

    /**
     * 新增鱼类
     *
     * @param fishCreateVO
     * @return
     */
    int createFish(FishCreateVO fishCreateVO);

    /**
     * 修改鱼类
     *
     * @param fishEditVO
     * @return
     */
    int editFish(FishEditVO fishEditVO);

    /**
     * 删除鱼类
     *
     * @param id
     * @return
     */
    int deleteFish(long id);

    /**
     * fish list转vo
     *
     * @param list
     * @return
     */
    List<FishListVO> fishListToVO(List<Fish> list);

    /**
     * fishVO list转vo
     *
     * @param list
     * @return
     */
    List<FishListVO> fishVOListToVO(List<FishListVO> list);

    /**
     * 查询是否已捕捉
     *
     * @param list
     * @param userId
     * @return
     */
    List<FishListVO> fishIsHave(List<FishListVO> list, long userId);
}
