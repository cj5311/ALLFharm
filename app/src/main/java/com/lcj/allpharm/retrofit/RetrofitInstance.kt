package com.lcj.allpharm.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Retrofit 인스턴스를 초기화하고 API 서비스에 접근하는 객체를 제공하는 객체
object RetrofitInstance {

    // API의 기본 URL을 상수로 정의
    private const val BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/"

    // Retrofit 객체를 초기화하는 부분. 'lazy' 키워드를 사용하여 최초 호출 시 한 번만 초기화되도록 설정.
    private val retrofit by lazy {

        Retrofit.Builder()// Retrofit.Builder()는 Retrofit 인스턴스를 생성하는 빌더 패턴을 사용
            // API의 기본 URL을 설정. 모든 요청은 이 URL을 기준으로 이루어짐
            .baseUrl(BASE_URL)
            // OkHttpClient를 설정하고, 요청 및 응답의 로그를 출력할 수 있도록 설정
            .client(
                // OkHttpClient의 빌더를 사용하여 커스터마이징
                OkHttpClient.Builder()
                    // HttpLoggingInterceptor를 추가하여 HTTP 요청과 응답의 로그를 확인할 수 있도록 설정
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        // 로그의 레벨을 'BODY'로 설정하여 요청과 응답 본문(body)까지 출력
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    // OkHttpClient를 빌드하여 클라이언트에 설정
                    .build()
            )
            // GsonConverterFactory를 추가하여 JSON 데이터를 자동으로 Kotlin 객체로 변환할 수 있도록 설정
            .addConverterFactory(GsonConverterFactory.create())
            // 최종적으로 Retrofit 객체를 빌드
            .build()
    }

    // 'YakService'라는 Retrofit 인터페이스를 초기화하여 API 호출을 위한 서비스 객체를 생성
    // 'lazy'를 사용하여 첫 번째 호출 시에만 객체가 생성되도록 설정
    val service: YakService by lazy {
        // Retrofit에서 정의한 service 인터페이스인 YakService를 생성
        retrofit.create(YakService::class.java)
    }
}