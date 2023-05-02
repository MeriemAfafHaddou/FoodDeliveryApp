package com.example.fooddeliveryapp


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun openPage(ctx: Context, url: String,weburl:String) {
    var intent:Intent
    try{
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    }
    catch(e:ActivityNotFoundException){
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(weburl))

    }
    ctx.startActivity(intent)
}
fun startCall(ctx: Context, number: String){
    val data = Uri.parse("tel:$number")
    val intent = Intent(Intent.ACTION_DIAL, data)
    ctx.startActivity(intent)
}
fun sendEmail(ctx: Context, receiver:String){
    val intent = Intent(Intent.ACTION_SEND)
    intent.data = Uri.parse("mailto:")
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
    ctx.startActivity(intent)
}

