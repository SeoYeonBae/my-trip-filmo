package com.ssafy.vue.plan.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.plan.service.PlanServcie;
import com.ssafy.vue.tourlist.model.TourListDto;

@RestController
@RequestMapping("/plan")
public class PlanController {
	private Logger logger = LoggerFactory.getLogger(PlanController.class);
	private PlanServcie planService;
	private Integer lastIdx;

	@Autowired
	public PlanController(PlanServcie planService) {
		super();
		this.planService = planService;
	}
	
	// 여행 계획 생성
	@PostMapping("/regist")
	public ResponseEntity<String> registPlan(@RequestBody PlanDto planDto) throws Exception {
		try {
			planService.regist(planDto);
			lastIdx = planService.getLastIdx(planDto.getUser_id());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	// 여행 계획 상세정보 등록
	@PostMapping("/regist/info")
	public ResponseEntity<String> registInfo(@RequestBody PlanDto planDto) throws Exception {
		try {
//			logger.info("여행 계획 상세정보 : {}", planDto.toString());
			planService.registInfo(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	// 방문할 여행지들 등록하기
	@PostMapping("/detail/{id}")
	public ResponseEntity<?> addPlanDetail(@RequestBody List<Integer> list, @PathVariable("id") String user_id) throws Exception {
		PlanIdxDto idxDto = new PlanIdxDto();
		try {
			for (int i = 0; i < list.size(); i++) {
//				logger.info("여행지 목록 : {}", list.get(i));
				idxDto.setIdx(list.get(i));
				idxDto.setPlan_idx(lastIdx);
				
				planService.addPlanDetail(idxDto);
			}	
			// 추가된 계획의 번호를 반환한다
			return new ResponseEntity<Integer>(lastIdx, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	// 여행 계획 삭제
	@DeleteMapping("/delete/{plan_idx}")
	public ResponseEntity<String> deletePlan(@PathVariable("plan_idx") int plan_idx) throws Exception {
		try {
			planService.deletePlan(plan_idx);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	// 여행 계획 상세정보, 방문지들 조회
	@GetMapping("/myplan/{idx}")
	public ResponseEntity<?> getMyPlanDetail(@PathVariable("idx") int plan_idx) throws Exception {
		try {
			List<Integer> myplanList = new ArrayList<Integer>();
			List<TourListDto> tourInfoList = new ArrayList<TourListDto>();

			myplanList = planService.getMyPlan(plan_idx);
			
			// 방문지들 받아오기
			for (int i = 0; i < myplanList.size(); i++) {
				tourInfoList.add(planService.getTourInfo(myplanList.get(i)));
			}	
			return new ResponseEntity<List<TourListDto>>(tourInfoList, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	// 나의 모든 여행 계획 조회
	@GetMapping("/total/planlist/{id}")
	public ResponseEntity<?> getMyPlanList(@PathVariable("id") String user_id) throws Exception {
		try {
			List<PlanDto> list = planService.getMyPlanList(user_id);
			for (int i = 0; i < list.size(); i++) {
				logger.info(list.get(i).toString());
			}
			return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}

	// 조회하려는 여행 계획의 정보
	@GetMapping("/myplan/info/{plan_idx}")
	public ResponseEntity<?> getMyPlanInfo(@PathVariable("plan_idx") int plan_idx) throws Exception {
		try {
			PlanDto planDto = planService.getMyPlanInfo(plan_idx);
			return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}

	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
