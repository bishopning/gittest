package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/brand")
//@Controller
@RestController //组合注解；包括了Controller ResponseBody两个注解；对该类的所有方法生效
public class BrandController {

    /**
     * 从注册中心获取该对象；在配置文件中已经指定了注册中心
     * @Autowired没有这个功能
     */
    @Reference
    private BrandService brandService;

    /**
     * 分页查询所有品牌列表
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/testPage")
    public List<TbBrand> testPage(Integer page,Integer rows){
        return (List<TbBrand>) brandService.findPage(page,rows).getRows();
    }

    /**
     * 获取并输出所有品牌列表
     * @return
     */
    @GetMapping("/findAll")
    //@RequestMapping(value = "/findAll", method =  RequestMethod.GET)
    //@ResponseBody
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    /**
     * 根据分页信息分页查询品牌数据
     * @param page 页号
     * @param rows 页大小
     * @return 分页对象
     */
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "rows",defaultValue = "10")Integer rows){
        return brandService.findPage(page,rows);
    }
}
