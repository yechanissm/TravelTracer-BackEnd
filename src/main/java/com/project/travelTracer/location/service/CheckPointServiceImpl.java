package com.project.travelTracer.location.service;


import com.project.travelTracer.location.dto.CheckPointDto;
import com.project.travelTracer.location.entity.CheckPoint;
import com.project.travelTracer.location.repository.CheckPointRepository;
import com.project.travelTracer.member.exception.MemberException;
import com.project.travelTracer.member.exception.MemberExceptionType;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CheckPointServiceImpl implements CheckPointService
{

    private final CheckPointRepository checkPointRepository;

    @Override
    public void CheckpointManager(CheckPointDto CheckPointDto) throws Exception {
        CheckPoint checkpoint = CheckPointDto.toEntity();
        checkpoint.addUserAuthority();

        checkPointRepository.save(checkpoint);
        System.out.println("성공?");
        log.info("save 메소드 실행");
    }
}
