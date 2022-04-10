package com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.output.NewOutput;
import com.dto.NewDTO;
import com.service.iNewService;

@CrossOrigin
@RestController
public class NewApi {

	@Autowired
	private iNewService newService;

	@GetMapping(value = "/new") // get, put, post trong API này chỉ có 1 hàm ko dc trùng nhau
	public NewOutput showNew(@RequestParam(value = "page", required = false) Integer page, 
							@RequestParam(value = "limit", required = false) Integer limit) {
		// required mặc định là true, thì oage và litmit bắt buộc phải khác null
		// truyền tham số vào URL dùng RequestParam và @PathVariable
		//Interger thì  mới xét null hay !null dc
		NewOutput result = new NewOutput();
		if (page != null && limit != null) {
			result.setPage(page);
			Pageable pageable = new PageRequest(page - 1, limit);// trừ 1 vì ở database nó tính là lấy phần tử 0 nên
			// theo công thức của Pageble là thế, cụ thể ở database tính là limit (0,2), limit (2, 2)
			result.setListResult(newService.findAll(pageable));
			result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
		} else {//load hết dữ liệu lên mà ko cần phân trang
			result.setListResult(newService.findAll());
		}
		return result;
	}

	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}

	@PutMapping(value = "/new/{id}") // truyền gia trị động {id} vao url
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return newService.save(model);
	}

	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
