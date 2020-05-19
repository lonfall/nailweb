package com.lh.nailweb.mapper.fish;

import com.lh.nailweb.entity.fish.Fish;
import com.lh.nailweb.vo.fish.FishVO;
import com.lh.nailweb.vo.page.fish.FishPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2020/5/13
 * @description:
 */
@Mapper
public interface FishMapper {
    List<Fish> getFishList(@Param("fish") FishVO fishVO, @Param("userId") long userId);

    List<Fish> getFishPage(FishPage page);

    int getFishPageTotal(FishPage page);

    int createFish(Fish fish);

    int editFish(Fish fish);

    int deleteFish(@Param("id") long id);
}
