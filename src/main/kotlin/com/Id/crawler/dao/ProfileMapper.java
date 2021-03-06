package com.Id.crawler.dao;

import com.Id.crawler.model.Profile;
import com.Id.crawler.model.ProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int countByExample(ProfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int deleteByExample(ProfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int insert(Profile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int insertSelective(Profile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    List<Profile> selectByExample(ProfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    Profile selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int updateByPrimaryKeySelective(Profile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table profile
     *
     * @mbggenerated Mon Apr 02 23:18:29 CST 2018
     */
    int updateByPrimaryKey(Profile record);
}