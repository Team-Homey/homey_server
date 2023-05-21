![image](https://user-images.githubusercontent.com/91039622/228971403-56b275bc-f13f-4b0c-9732-cc0b9a88c83d.png)

# 2023 Solution Challenge - Homey 🏠
Korea University GDSC - Team homey Server repository
<details>
<summary> API 명세서 </summary>
	
# 1. Authentication

- **POST - /authentication** : 로그인
    
    ```jsx
    //Request
    {
    	"email" : "dldyghks951@gmail.com",
    	"name" : "이요환"
    }
    
    //Response
    {
      "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd0b3dlckBrb3JlYS5hYy5rMTIzciIsImV4cCI6MTY3ODAwNjQ1NCwiaWF0IjoxNjc3OTIwMDU0fQ.xIrLzvGAG-Yzr47T2_BsolhJaBYk1M5UlcxRO33jL_c",
      "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd0b3dlckBrb3JlYS5hYy5rMTIzciIsImV4cCI6MTY4NTg2ODg1NCwiaWF0IjoxNjc3OTIwMDU0fQ.A9AK2Gye6SLs3BfKITWrzFxQijUzjN13flGSbL8Lzxk",
      "alreadyRegistered": false
    }
    ```
    
- **POST - /authentication/refresh** : accessToken 재발급. 다른 모든 요청에서 403 발생 시, 호출해야함
    
    ```jsx
    //Request
    {
    	"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd0b3dlckBrb3JlYS5hYy5rMTIzciIsImV4cCI6MTY4NTg2ODg1NCwiaWF0IjoxNjc3OTIwMDU0fQ.A9AK2Gye6SLs3BfKITWrzFxQijUzjN13flGSbL8Lzxk"
    }
    
    //Response
    {
    	"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd0b3dlckBrb3JlYS5hYy5rMTIzciIsImV4cCI6MTY3ODAwNjU3MiwiaWF0IjoxNjc3OTIwMTcyfQ.VYE067IHBG9TNNW6rH-iJ82uk-2JeEUovuFFTMQKu1E"
    }
    
    //refreshToken이 유효하지 않으면, 401 unAuthorized -> 다시 로그인 요구
    ```
    

# 2. User

1. enum 속성 (괄호 안에 있는 문자열 형식대로)
    
    ```jsx
    //emotion 속성
    EXCITED("EXCITED"),
        HAPPY("HAPPY"),
        SAD("SAD"),
        ANGRY("ANGRY"),
        LOVELY("LOVELY"),
        SOSO("SOSO"),
        UNKNOWN("UNKNOWN");
    
    //familyRole 속성
    FAMILY_ROLE_FATHER("FAMILY_ROLE_FATHER"),
        FAMILY_ROLE_MOTHER("FAMILY_ROLE_MOTHER"),
        FAMILY_ROLE_PARENT("FAMILY_ROLE_PARENT"),
        FAMILY_ROLE_GRANDPARENT("FAMILY_ROLE_GRANDPARENT"),
        FAMILY_ROLE_CHILD("FAMILY_ROLE_CHILD"),
        FAMILY_ROLE_DAUGHTER("FAMILY_ROLE_DAUGHTER"),
        FAMILY_ROLE_SON("FAMILY_ROLE_SON");
    ```
    
- **PATCH - /user** : 유저(본인) 정보 update (첫 로그인일 때, 사용자로부터 추가 정보 입력받아서 보내는 요청)
    
    ```jsx
    //Request
    {
            "age" : 12,
            "gender" : "남자",
    				"name": "이름",
            "address" : "서울 특별시 동대문구 왕산로9가길 47",
            "birth" : "1999-04-19", //yyyy-MM-dd 
            "familyRole" : "FAMILY_ROLE_FATHER"
    }
    
    //response
    {
        "id": 2,
        "regDate": "2023-03-04T21:15:17.480041"
    }
    ```
    
- **GET - /user/{id}** : 특정 유저 정보
    
    ```jsx
    //Response
    {
    		"id": 2,
        "email": "dldyghks951@gmail.co2m",
    		"name": "이름",
        "age": 12,
        "gender": "남자",
        "address": "서울 특별시 동대문구 왕산로9가길 47",
        "picture": "picture",
        "regDate": "2023-03-04T21:15:17.480041",
        "birth": "1999-04-19",
        "familyRole": "FAMILY_ROLE_FATHER",
    //    "emotion": "EXCITED"
    }
    ```
    
- **GET - /user/my-info** : 유저 본인 정보
    
    ```jsx
    //Response
    {
    		"id": 2,
        "email": "dldyghks951@gmail.co2m",
    		"name": "이름",
        "age": 12,
        "gender": "남자",
        "address": "서울 특별시 동대문구 왕산로9가길 47",
        "picture": "picture",
        "regDate": "2023-03-04T21:15:17.480041",
        "birth": "1999-04-19",
        "familyRole": "FAMILY_ROLE_FATHER",
        //"emotion": "EXCITED"
    }
    ```
    
- **POST - /user/emotion** : 유저 emotion update
    
    ```jsx
    //Request
    {
    	"emotion" : "EXCITED"
    }
    
    //Response
    {
    	"emotion" : "EXCITED"
    }
    ```
    
- **GET- /user/my-emotion** : 유저 emotion 반환
    
    ```jsx
    //Response
    {
    	"emotion" : "EXCITED"
    }
    ```
    
- **POST - /user/family** : 유저 본인 family update
    
    ```jsx
    //Request
    {
        "hashCode" : "62ef9f8d-6f88-4c24-8d6b-8567495db1db"
    }
    ```
    
- **POST - /user/image** : 유저 본인 image 저장
    
    ```jsx
    //Request
    form-data로 "image" : {이미지 파일}
    
    ```
    

# 3. Family

- **POST - /family** : family 생성 (요청 보낸 사용자 자동으로 연결됨)
    
    ```jsx
    //Request
    {
    	"name" : "yohwnaFamily"
    }
    
    //Response
    {
        "id": 102,
        "name": "yohwnaFamily",
        "code": "62ef9f8d-6f88-4c24-8d6b-8567495db1db",
        "regDate": "2023-03-04T22:37:33.5721847"
    }
    ```
    
- **GET - /family/my-family** : 유저 family 정보 반환
    
    ```jsx
    
    //Response
    {
        "id": 102,
        "name": "yohwnaFamily",
        "code": "62ef9f8d-6f88-4c24-8d6b-8567495db1db",
        "regDate": "2023-03-04T22:37:33.572185",
    		"point" : 3,
        "users": [
            {
                "id": 2,
                "email": "dldyghks951@gmail.co2m",
    						"name": "이름",
                "age": 12,
                "gender": "남자",
                "address": "서울 특별시 동대문구 왕산로9가길 47",
                "picture": "picture",
                "regDate": "2023-03-04T21:15:17.480041",
                "birth": "1999-04-19",
                "familyRole": "FAMILY_ROLE_FATHER",
                "emotion": "EXCITED"
            },
    				{
                "id": 3,dPq
                "email": "dldyghks951@gmail.co2m",
    						"name": "이름",
                "age": 12,
                "gender": "남자",
                "address": "서울 특별시 동대문구 왕산로9가길 47",
                "picture": "picture",
                "regDate": "2023-03-04T21:15:17.480041",
                "birth": "1999-04-19",
                "familyRole": "FAMILY_ROLE_FATHER",
                "emotion": "EXCITED"
            }
        ]
    }
    ```
    
- **GET - /family/ids : 존재하는 가족 id 모두 반환**
    
    ```jsx
    //response
    {
        "familyIds": [
            1,
            2,
            52,
            102,
            152,
            202
        ]
    }
    ```
    

# 4. Photo

- **GET - /photo/family** : 가족의 사진 모두 반환
    
    ```jsx
    //Response
    [
        {
            "id": 52,
            "image": "https://storage.googleapis.com/homey-test-storage/81ed9e8e-0a0a-4852-b69b-783b21f5eb3f",
            "title": "my photo3",
            "regDate": "2023-03-05T23:18:20.646312"
        },
        {
            "id": 53,
            "image": "https://storage.googleapis.com/homey-test-storage/fc4a179a-2b53-4e7b-8cdb-498bde9a3a35",
            "title": "my photo4",
            "regDate": "2023-03-05T23:24:30.232888"
        },
    		...
    ]
    ```
    
- **GET - /photo/my-photo** : 본인의 사진 모두 반환
    
    ```jsx
    //Response
    [
        {
            "id": 52,
            "image": "https://storage.googleapis.com/homey-test-storage/81ed9e8e-0a0a-4852-b69b-783b21f5eb3f",
            "title": "my photo3",
            "regDate": "2023-03-05T23:18:20.646312"
        },
        {
            "id": 53,
            "image": "https://storage.googleapis.com/homey-test-storage/fc4a179a-2b53-4e7b-8cdb-498bde9a3a35",
            "title": "my photo4",
            "regDate": "2023-03-05T23:24:30.232888"
        },
    		...
    ]
    ```
    
- **GET - /photo/{id}**: photo 정보 반환
    
    ```jsx
    //Response
    {
        "id": 1,
        "image": "https://storage.googleapis.com/homey-test-storage/9e78676e-10cd-44ba-b9de-b9528376b64a",
        "title": "my photo",
        "regDate": "2023-03-05T18:19:30.264016"
    }
    ```
    
- **POST - /photo/content** : Photo 제목만  넘기면, db에 인스턴스 생성해서 id 반환해줌
    
    ```jsx
    //Reqeust - formdata로 보내야함
    {
    	"title":"my photo"
    }
    
    //Response
    
    {
        "id": 1,
        "image": "https://storage.googleapis.com/homey-test-storage/9e78676e-10cd-44ba-b9de-b9528376b64a",
        "title": "my photo",
        "regDate": "2023-03-05T18:19:30.2640164"
    }
    ```
    
- **POST - /photo/{id}** : Photo image 파일 저장. **/photo/content의 바로 다음 요청. 반환받은 id값을 uri에 넣어서 image 파일 form-data로 넘겨주면 됨**
    
    ```jsx
    //Reqeust - formdata로 보내야함
    "image" : {이미지 파일}
    ```
    
- **DELETE - /photo/{id}** : photo 삭제
- **PATCH - /photo/{id}** : photo title 수정
    
    ```jsx
    //Request
    {
        "id": 1,
        "image": "https://storage.googleapis.com/homey-test-storage/9e78676e-10cd-44ba-b9de-b9528376b64a",
        "title": "my photo",
        "regDate": "2023-03-05T18:19:30.264016"
    }
    ```
    
- **GET - /photo/family/{id}** : id에 해당하는 가족의 갤러리 images URL 모두 반환
    
    ```jsx
    *//response
    {
    	"address" : "서울특별시 동대문구 왕산로 19라길 47",
    	"images" : [
    		"https://storage.googleapis.com/homey-test-storage/0f6c85f6-84cc-41de-a3dc-c691a4a54a9d",
    		"https://storage.googleapis.com/homey-test-storage/0f6c85f6-84cc-41de-a3dc-faopv89daf21",
    		"https://storage.googleapis.com/homey-test-storage/0f6c85f6-84cc-41de-a3dc-fdkabsa0223",
    		"https://storage.googleapis.com/homey-test-storage/0f6c85f6-84cc-41de-a3dc-kwejfksd9124"*
    	]
    }
    ```
    

# 5. Question

- **POST - /question/family** : 질문 등록 요청 (요청하면 (매일 자정 등) Question 인스턴스 추가해줌)
    
    ```jsx
    //Request
    {
    	"familyId" : 1
    }
    ```
    
- **GET - /question/family** : 가족에게 할당된 모든 질문 반환 (answer api 작성 이후 질문에 대한 답변 정보도 포함되어야함) 로직 수정 필요
    
    ```jsx
    //Response
    [
        {
            "id": 102,
            "content": "When was your most enjoyable family trip?"
        },
        {
            "id": 103,
            "content": "Who is the funniest person in my family?"
        },
        {
            "id": 104,
            "content": "What do you want to do with your family on your upcoming birthday?"
        }
    ]
    ```
    
- **GET - /question/{id}** : 특정 질문 세부 정보 반환
    
    ```jsx
    //Response
    {
        "id": 1,
        "content": "가장 기억에 남는 가족 여행은 언제였나요?",
        "answerList": [
            {
                ****"id": 1,
                "content": "제주도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
                "regDate": "2023-03-06T15:55:38.790904",
                "name": "이름"
            },
            {
                "id": 2,
                "content": "강원도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
                "regDate": "2023-03-06T16:51:45.061442",
                "name": "이름"
            },
            {
                "id": 3,
                "content": "일본 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
                "regDate": "2023-03-06T16:52:51.664787",
                "name": "이름"
            }
        ]
    }
    ```
    

# 6. Answer

- **POST - /answer/question/{questionId}** : 응답 등록
    
    ```jsx
    //Request
    {
        "content" : "제주도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲"
    }
    ```
    
- **GET - /answer/{id}** : 특정 answer 세부 정보 반환
    
    ```jsx
    //Response
    {
        "id": 52,
        "content": "제주도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
        "regDate": "2023-03-10T15:55:34.059328",
        "name": "이름",
        "userId": 202,
        "questionId": 52,
        "comments": [
            {
                "content": "이요환 바보",
                "name": "이름",
                "regDate": "2023-03-10T16:34:11.838601"
            },
            {
                "content": "이요환 바보바보",
                "name": "이름",
                "regDate": "2023-03-10T16:36:05.901908"
            },
            {
                "content": "이요환 바보바보2",
                "name": "이름",
                "regDate": "2023-03-10T16:36:09.193868"
            }
        ]
    }
    ```
    
- **GET - /answer/question/{questionId}** : 특정 question에 대한 답변 정보 모두 반환
    
    ```jsx
    //Response
    [
        {
            "id": 1,
            "content": "제주도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
            "regDate": "2023-03-06T15:55:38.790904",
            "name": "username"
        },
        {
            "id": 2,
            "content": "강원도 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
            "regDate": "2023-03-06T16:51:45.061442",
            "name": "username"
        },
        {
            "id": 3,
            "content": "일본 여행 어쩌구 ~~!@$@!$#@ㅣㅅㅎㄴ라ㅣㅌ푸ㅠㅏㅣㄴㅇㄻ;나리위ㅠㅌ칲",
            "regDate": "2023-03-06T16:52:51.664787",
            "name": "username"
        }
    ]
    ```
    

# 7. Comment

- **POST - /comment/answer/{answerId}**: 댓글 등록
    
    ```jsx
    //Request
    {
        "content" : "이요환 바보"
    }
    ```
    
- GET - /comment

# 8. RecommendedContent

- **POST - /recommended-content/family-id/{id}** : 추천 정보 저장
    
    ```jsx
    //request
    {
    	"title" : "test title",
    	"address" : "test address",
    	"picture" : "https://1234514",
    	"url" : "https://rdgd"
    }
    ```
    
- GET**- /recommended-content** : 추천 정보 반환
    
    ```jsx
    //response
    [
        {
            "title": "test title",
            "address": "test address",
            "picture": "https://1234514",
            "url": "https://rdgd"
        },
        {
            "title": "test title3",
            "address": "test address",
            "picture": "https://1234514",
            "url": "https://rdgd"
        },
        {
            "title": "test title2",
            "address": "test address",
            "picture": "https://1234514",
            "url": "https://rdgd"
        }
    ]
    ```
    

# 9. RelationshipInventory

- **POST - /relationshipinventory** : 검사 결과 등록

```jsx
//Request (항목 순서대로 고른 번호 문자열)
{
	"result" : "123412341234533224"
}

```

- **GET - /relationshipinventory :** 가족의 검사 결과 반환
    
    ```jsx
    //response
    {
    	"communication" : 3,
    	"togetherness" : 2,
    	"positivity" : 3,
    	"caring" : 0,
    	"conflctResolution" : 1,
    	"result" : 3
    }
    ```
</details>
