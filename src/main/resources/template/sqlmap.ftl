<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${daoName}">

    <resultMap id="${param}ResultForList" type="${paramType}">
    <#list cols as col>
         <result column="${col.actualColumnName}" property="${col.domainColumnName}" jdbcType="${col.jdbcTypeName}" />
    </#list>
    </resultMap>

    <resultMap id="${param}Result" type="${paramType}" extends="${param}ResultForList">

    </resultMap>

    <sql id="sql-${param}">
    <#list cols as col>
    <#--<#if col.jdbcTypeName ='TIMESTAMP' || col.jdbcTypeName ='DATE'>
        <if test="(${col.domainColumnName} != null )"> and t.${col.actualColumnName} = ${"#{" + col.domainColumnName +"}"} </if>
    <#else>
        <if test="(${col.domainColumnName} != null and ${col.domainColumnName} != '')"> and t.${col.actualColumnName} = ${"#{" + col.domainColumnName +"}"} </if>
    </#if>-->
        <if test="(${col.domainColumnName} != null )"> and t.${col.actualColumnName} = ${"#{" + col.domainColumnName +"}"} </if>
    </#list>
    </sql>

    <insert id="insert${param?cap_first}" parameterType="${paramType}">
        <selectKey resultType="String"  order="BEFORE"  keyProperty="id">SELECT CAST(NEWID() AS VARCHAR(36)) AS ID</selectKey>
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list cols as col>
       <#-- <#if col.jdbcTypeName ='TIMESTAMP' || col.jdbcTypeName ='DATE'>
            <if test="(${col.domainColumnName} != null )"> ${col.actualColumnName} , </if>
        <#else>
            <if test="(${col.domainColumnName} != null and ${col.domainColumnName} != '')"> ${col.actualColumnName} , </if>
         </#if>-->
            <if test="(${col.domainColumnName} != null )"> ${col.actualColumnName} , </if>
        </#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list cols as col>
      <#--  <#if col.jdbcTypeName ='TIMESTAMP' || col.jdbcTypeName ='DATE'>
            <if test="(${col.domainColumnName} != null )">${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"},  </if>
        <#else>
            <if test="(${col.domainColumnName} != null and ${col.domainColumnName} != '')"> ${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"},  </if>
        </#if>-->
            <if test="(${col.domainColumnName} != null )">${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"},  </if>
        </#list>
        </trim>
    </insert>

    <update id="update${param?cap_first}" parameterType="${paramType}">
        UPDATE ${tableName}
        <set>
            <trim suffixOverrides=",">
            <#list cols as col>
           <#--  <#if col.jdbcTypeName ='TIMESTAMP' || col.jdbcTypeName ='DATE'>
                <if test="(${col.domainColumnName} != null )"> ${col.actualColumnName} = ${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"}, </if>
             <#else>
                <if test="(${col.domainColumnName} != null and ${col.domainColumnName} != '')"> ${col.actualColumnName} = ${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"}, </if>
             </#if>-->
                <if test="(${col.domainColumnName} != null )"> ${col.actualColumnName} = ${r"#{"}${col.domainColumnName} , jdbcType= ${col.jdbcTypeName } ${r"}"}, </if>
            </#list>
            </trim>
        </set>
        <where>
        <#list pkList as pk>
            <#--<if test="(${pk.domainColumnName} != null and ${pk.domainColumnName} != '')"> and ${pk.actualColumnName} = ${r'#{'} ${pk.domainColumnName}, jdbcType= ${pk.jdbcTypeName } ${r'}'}   </if>-->
            <if test="(${pk.domainColumnName} != null )"> and ${pk.actualColumnName} = ${r'#{'} ${pk.domainColumnName}, jdbcType= ${pk.jdbcTypeName } ${r'}'}   </if>-->
        </#list>
            <if test="${pkListStr} and (map.pks != null and map.size > 0)">
                and (${pkString}) in
                <foreach item="item" collection="map.pks" open="(" separator="," close=")"> ${r"#{item}"}</foreach>
            </if>
        </where>
    </update>

    <delete id="delete${param?cap_first}" parameterType="${paramType}">
        delete from  ${tableName}
        <where>
        <#list pkList as pk>
            <#--<if test="(${pk.domainColumnName} != null and ${pk.domainColumnName} != '')"> and ${pk.actualColumnName} = ${r'#{'} ${pk.domainColumnName} ${r'}'}   </if>-->
        <if test="(${pk.domainColumnName} != null )"> and ${pk.actualColumnName} = ${r'#{'} ${pk.domainColumnName} ${r'}'}   </if>
        </#list>
            <if test="${pkListStr} and (map.pks != null and map.size > 0)">
                and (${pkString}) in
                <foreach item="item" collection="map.pks" open="(" separator="," close=")"> ${r"#{item}"}</foreach>
            </if>
        </where>
    </delete>


    <select id="select${param?cap_first}" resultMap="${param}Result" parameterType="${paramType}">
        SELECT * FROM ${tableName} t
        <where>
            <include refid="sql-${param}" />
        </where>
    </select>

    <select id="select${param?cap_first}List" resultMap="${param}ResultForList" parameterType="${paramType}">
        SELECT
        <if test="row.count != null and row.count != ''"> top ${r"${row.count}"} </if>
        * FROM ${tableName} t
        <where>
            <include refid="sql-${param}" />
        </where>
        ORDER BY ${pks} ASC
    </select>

    <select id="select${param?cap_first}Count" resultType="int" parameterType="${paramType}">
        select count(1) from  ${tableName} t
        <where>
            <include refid="sql-${param}" />
        </where>
    </select>

    <select id="select${param?cap_first}PageList" resultMap="${param}Result" parameterType="${paramType}">
        SELECT * FROM (
        SELECT t.* ,
        ROW_NUMBER() OVER ( ORDER BY ${pks} DESC ) AS _RN
        FROM ${tableName} t
        <where>
            <include refid="sql-${param}" />
        </where>
        <!-- order by ${pks} asc -->
        ) TEMP_TABLE WHERE _RN BETWEEN  ${r"#{row.first}"} + 1 AND ${r"#{row.first}"} + ${r"#{row.count}"}
    </select>


</mapper>