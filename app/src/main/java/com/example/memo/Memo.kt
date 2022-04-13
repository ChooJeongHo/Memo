package com.example.memo

class Memo {
    var name : String = "" // 카테고리 명
    var content : String = "" // 내용

    constructor(name: String, content: String) {
        this.name = name
        this.content = content
    }
}