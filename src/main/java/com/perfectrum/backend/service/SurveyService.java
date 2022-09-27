package com.perfectrum.backend.service;

import com.perfectrum.backend.domain.entity.PerfumeEntity;
import com.perfectrum.backend.dto.survey.SurveyDto;

public interface SurveyService {

    PerfumeEntity surveyResult(String decodeId, SurveyDto surveyDto);

}
