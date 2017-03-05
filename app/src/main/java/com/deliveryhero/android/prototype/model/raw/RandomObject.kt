package com.deliveryhero.android.prototype.model.raw

data class RandomObject(val id: String,
                        val name: String,
                        val bannerUrl: String,
                        val description: String,
                        val recommendations: List<String>)