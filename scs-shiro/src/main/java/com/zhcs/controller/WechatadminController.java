package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.WechatadminEntity;
import com.zhcs.service.WechatadminService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:wechatadminController</p>
 * <p>Description: 微信关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("wechatadmin")
public class WechatadminController extends AbstractController  {
	@Autowired
	private WechatadminService wechatadminService;
	
	@RequestMapping("/wechatadmin.html")
	public String list(){
		return "wechatadmin/wechatadmin.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("wechatadmin:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<WechatadminEntity> wechatadminList = wechatadminService.queryList(map);
		int total = wechatadminService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(wechatadminList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wechatadmin:info")
	public R info(@PathVariable("id") Long id){
		WechatadminEntity wechatadmin = wechatadminService.queryObject(id);
		
		return R.ok().put("wechatadmin", wechatadmin);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("wechatadmin:save")
	public R save(@RequestBody WechatadminEntity wechatadmin){
	
		BeanUtil.fillCCUUD(wechatadmin, getUserId(), getUserId());
		wechatadminService.save(wechatadmin);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wechatadmin:update")
	public R update(@RequestBody WechatadminEntity wechatadmin){
		
		BeanUtil.fillCCUUD(wechatadmin, getUserId());
		wechatadminService.update(wechatadmin);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("wechatadmin:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			wechatadminService.delete(id);
		} else {
			WechatadminEntity wechatadmin = new WechatadminEntity();
			wechatadmin.setId(id);
			/*wechatadmin.setStatus("0");*/
			BeanUtil.fillCCUUD(wechatadmin, getUserId());
			wechatadminService.update(wechatadmin);
		}
		return R.ok();
	}
	
}
