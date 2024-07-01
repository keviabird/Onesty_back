package com.onesty.services.management.service.mapper;

import com.onesty.api.core.test.Answer;
import com.onesty.api.core.test.Question;
import com.onesty.api.core.test.TestManagement;
import com.onesty.services.management.persistence.AnswerEntity;
import com.onesty.services.management.persistence.QuestionEntity;
import com.onesty.services.management.persistence.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    TestEntity apiToEntity(TestManagement api);

    @Mapping(target = "version", ignore = true)
    QuestionEntity apiToEntity(Question api);

    @Mapping(target = "version", ignore = true)
    AnswerEntity apiToEntity(Answer api);

    Question entityToApi(QuestionEntity question);

    Answer entityToApi(AnswerEntity answer);
}
