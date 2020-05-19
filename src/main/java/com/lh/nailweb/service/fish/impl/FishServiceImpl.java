package com.lh.nailweb.service.fish.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.entity.fish.Fish;
import com.lh.nailweb.mapper.fish.FishMapper;
import com.lh.nailweb.service.fish.IFishService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.fish.FishCreateVO;
import com.lh.nailweb.vo.fish.FishEditVO;
import com.lh.nailweb.vo.fish.FishVO;
import com.lh.nailweb.vo.page.fish.FishPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description:
 */
@Service
public class FishServiceImpl implements IFishService {

    @Autowired
    private FishMapper mapper;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public List<Fish> getFishList(FishVO fishVO, long userId) {
        return mapper.getFishList(fishVO, userId);
    }

    @Override
    public FishPage getFishPage(FishPage page, long userId) {
        List<Fish> list = mapper.getFishPage(page);
        page.setData(list);
        page.setTotal(mapper.getFishPageTotal(page));
        return page;
    }

    @Override
    public int createFish(FishCreateVO fishCreateVO) {
        Fish fish = new Fish();
        fish.setId(snowFlakeUtil.nextId());
        fish.setName(fishCreateVO.getName());
        fish.setImg(fishCreateVO.getImg());
        fish.setPrice(fishCreateVO.getPrice());
        fish.setSouth_month(fishCreateVO.getSouth_month());
        fish.setNorth_month(fishCreateVO.getNorth_month());
        fish.setTime(fishCreateVO.getTime());
        fish.setPlace(fishCreateVO.getPlace());
        fish.setSize(fishCreateVO.getSize());
        fish.setCreateTime(new Date());
        fish.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return mapper.createFish(fish);
    }

    @Override
    public int editFish(FishEditVO fishEditVO) {
        Fish fish = new Fish();
        fish.setId(fishEditVO.getId());
        fish.setName(fishEditVO.getName());
        fish.setImg(fishEditVO.getImg());
        fish.setPrice(fishEditVO.getPrice());
        fish.setSouth_month(fishEditVO.getSouth_month());
        fish.setNorth_month(fishEditVO.getNorth_month());
        fish.setTime(fishEditVO.getTime());
        fish.setPlace(fishEditVO.getPlace());
        fish.setSize(fishEditVO.getSize());
        fish.setUpdateTime(new Date());
        return mapper.editFish(fish);
    }

    @Override
    public int deleteFish(long id) {
        return mapper.deleteFish(id);
    }
}
