package com.eroad.project.dao;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SVcode;

public interface SVcodeMapper extends Mapper<SVcode> {

	SVcode findAbleCode(String mobileNo);
}