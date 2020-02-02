package co.laomag.immigration.controller;

import co.laomag.immigration.common.PageRequestHelper;
import co.laomag.immigration.common.ProgramsRepository;
import co.laomag.immigration.common.StringUtil;
import co.laomag.immigration.entity.Programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目部分代码
 * 增删改  展示
 */
@Controller
public class ProgramsController {
	@Autowired
	private ProgramsRepository programsRepository;
	/**
	 * 前台页面展示
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/programs/main")
	public Map<String,Object> main(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Specification<Programs> spec = new Specification<Programs>() {
				private static final long serialVersionUID = 1L;
				@Override
				public Predicate toPredicate(Root<Programs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();
					list.add(cb.equal(root.get("programStatus"),2));

					Predicate[] p2 = new Predicate[list.size()];
					query.where(cb.and(list.toArray(p2)));
					return query.getRestriction();
				}
			};
			List<Programs> list = programsRepository.findAll(spec);
			map.put("data",list);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/programs/main/detail")
	public Map<String,Object> mainDetail(String id, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Programs p = programsRepository.findById(id).get();
			map.put("data",p);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 后台管理
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/programs/list")
	public Map<String,Object> list(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Pageable pageable = PageRequestHelper.buildPageRequest(request, null);
			Page<Programs> pageList = programsRepository.findAll(pageable);
			map.put("code", 0);
			map.put("msg", "查询成功");
			map.put("count", pageList.getTotalElements());
			map.put("data",pageList.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/programs/dell")
	public Map<String,Object> dell(String id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//判断是否绑定数据
			programsRepository.deleteById(id);
			map.put("code", 0);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 编辑
	 * @param id
	 */
	@RequestMapping("/programs/edit")
	public String edit(String id, ModelMap model) {
		try {
			Programs p = null;
			if(StringUtil.isNotBlank(id)) {
				p = programsRepository.findById(id).get();
				model.put("programs", p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "page/programs-edit";
	}
	@ResponseBody
	@RequestMapping("/programs/save")
	public Map<String,Object> save(Programs p, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			programsRepository.save(p);
			map.put("code", 0);
			map.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/programs/status")
	public Map<String,Object> status(String id, Integer status, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		try {
			Programs p = programsRepository.findById(id).get();
			if(p!=null) {
				p.setProgramStatus(status);
				programsRepository.save(p);
				map.put("msg", "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
