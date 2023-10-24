# wanted-pre-onboarding-backend
원티드 채용공고 구현 사전과제
<br>
- CSR 방식으로 구현했습니다.
- skill: spring boot, spring data jpa, Mysql

## [troubleshooting] 
- git reset --hard abcdef 오용으로 인한 이슈 발생 > 커밋 내역 삭제 
- 백업해둔 최종본으로 로컬 최신화 후 강제 push.

<br>

## API 명세서 
https://documenter.getpostman.com/view/22700146/2s9YR56EaX

## 요구사항 
1. 채용공고를 등록합니다.
   ```
   Example)
# 데이터 예시이며, 필드명은 임의로 설정가능합니다.
{
  "회사_id":회사_id,
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1000000,
  "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "사용기술":"Python"
}```



<br>

## 구현 기능 목록 
1. 채용공고 등록 
- RecruitmentController의 post()의 매개변수로 companyId를 pathvariable로, RequestBody로 등록 내용을 전달받습니다. 
- mapper를 통해 역직렬화를 적용 후 반환된 객체를 RecruitmentService의 createRecruitment로 전달합니다. 
- findPostedCompany 메서드를 이용해 companyRepository에 회사가 존재하는지 판단합니다. 
- 회사가 존재하지 않는다면 예외를 발생시킵니다. 
- recruitmentRepository.save()로 등록하고자 하는 내용을 데이터베이스에 저장합니다.
- 변경된 내용을 직렬화 해 클라이언트에게 전달합니다.  


2. 채용공고 수정 
- RecruitmentController의 update메소드를 통해 recruitmentId와 RequestBody로 수정 내용을 전달받습니다. 
- mapper 클래스로 역직렬화 후 recruitmentService.updateRecruitment()를 호출합니다.
- findRecruitment로 채용 공고 존재 여부를 판단합니다. 
- updateRecruitment로 controller에서 전달 받은 객체의 속성이 null이 아니라면 해당 속성을 수정합니다.
- recruitmentRepository.save()로 데이터베이스에 저장 후 변경된 객체를 반환합니다. 
- 변경된 내용을 직렬화 해 클라이언트에게 전달합니다. 


3. 채용공고 삭제 
- RecruitmentController의 remove메소드를 통해 recruitmentId를 전달받습니다. 
- recruitmentService.deleteRecruitment메소드에 recruitmentId를 전달합니다. 
- findRecruitment로 존재하는 채용공고를 불러옵니다. 
- recruitmentRepository.delete()로 데이터베이스에서 삭제합니다. 


4-1. 채용공고 목록 조회
- RecruitmentController의 list메소드로 page, size 값을 전달받습니다. 
- ecruitmentService.findAll()를 호출해 pageable 객체를 생성하고 recruitmentRepository.findAll()을 통해 Page<Recruitment> 객체들을 불러옵니다. 
- 해당 객체들의 content를 추출 후 List<Recruitment>타입으로 반환합니다. 
- MultiResponseDto를 통해 PageInfo를 적용하여 페이지네이션을 구현합니다. 


4-2 채용공고 검색 기능 
- RecruitmentController의 search 메소드로 page, size, 그리고 검색을 위한 keyword를 전달받습니다. 
- recruitmentService.searchRecruitment를 호출하고 pageable 객체를 생성해 recruitmentRepository.searchByKeyword 메소드를 호출합니다. 
- keyword를 기준으로 모든 컬럼명에서 일치하는 값을 반환합니다. 
- 직렬화 후 MultiResponseDto를 이용해 클라이언트에게 반환합니다. 


5. 채용 상세 페이지 조회 
- RecruitmentController의 findDetail메소드로 recruitmentId를 전달받습니다. 
- recruitmentId를  기준으로 데이터베이스에서 객체를 가져옵니다. 객체가 존재하지 않는다면 예외를 발생시킵니다. 
- mapper클래스의 recruitmentToDetailResponseDto에서 요구 값들과 동일한 회사에서 업로드한 채용공고의 리스트를 추가해 반환합니다. 
- 직렬화 후 클라이언트에게 반환합니다. 



6. 사용자 채용공고 지원 
- UserController의 submission 메소드로 userId와 recruitmentId를 전달 받습니다. 
- userService.apply를 호출합니다. 
- findUser로 공고 지원 여부를 판단합니다. 지원시 예외를 발생시킵니다. 
- 지원하지 않았다면, recruitmentId를 기준으로 Recruitment를 가져온 뒤, users 객체를 생성해 필드값들을 채운 뒤 데이터베이스에 저장합니다. 
<br>

## ERD

<img width="80%" src="https://github.com/11dy/wanted-pre-onboarding-backend/assets/96255906/5a6575d4-a8ec-42a7-9040-db55b0dbb75c"/>

<br>

## ✉️ Commit Message

| 이름     | 내용                                                                  |
| -------- | --------------------------------------------------------------------- |
| feat     | 새로운 기능 추가                                                      |
| fix      | 버그 수정                                                             |
| docs     | 문서 수정                                                             |
| style    | 코드 스타일 변경 (코드 포매팅, 세미콜론 누락 등)기능 수정이 없는 경우 |
| design   | 사용자 UI 디자인 변경 (CSS 등)                                        |
| test     | 테스트 코드, 리팩토링 테스트 코드 추가                                |
| refactor | 코드 리팩토링                                                         |
| build    | 빌드 파일 수정                                                        |
| ci       | CI 설정 파일 수정                                                     |
| perf     | 성능 개선                                                             |
| chore    | 빌드 업무 수정, 패키지 매니저 수정 (gitignore 수정 등)                |
| rename   | 파일 혹은 폴더명을 수정만 한 경우                                     |
| remove   | 파일을 삭제만 한 경우                                                 |
