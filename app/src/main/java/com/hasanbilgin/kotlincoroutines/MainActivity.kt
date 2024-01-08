package com.hasanbilgin.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //****************************************

        //coroutines uzun süren tasklerde - bişey çekme işlemleri-uzun süren işlemlerde ve  main bloklamadan lite bir threadding yapısıdır
        //-coroutineler thread lere göre çok daha hafiftir
        //https://github.com/Kotlin/kotlinx.coroutines


        //threadlerde 10bin yapmak çökertir ama bu coroutineste 1 milyon dahi koymaz
        //Light Weightness
        //100bin tane coroutines oluşturuyoruz ve çalıştırıyoruz
        /*
        var i=0
        GlobalScope.launch {
            //repeat(100000)//aynı
            repeat(100_000){
                println("android"+i)
                i++
            }
        }

         */

        //****************************************

        //scope -kapsam- nerede coroutines çalıştıracağımız belirleyen ve yaşam döngüsü belirleyen br yapıdır
        //Global Scope bütün application içersinde çalıştıracağımız bir kapsamda açan yapı
        //runBlociking //cope oluşturuyor ama bloklayarak oluşturuyor
        //CoroutineScope içersinde bütün coroutines bitene kadar devam ediyo

        //runBlocking
        /*
        println("run blocking start")
        runBlocking {
            launch {
                delay(5000)//5sn
                println("run blocking")
            }
        }
        println("run blocking end")
        //launch çalıştırmak
        //delay 5sn bekletmek için
        //çalıştır     println("run blocking start") yazar 5sn aonra  println("run blocking") sonra bu yazar   println("run blocking end")
        */

        /*
        //GlobalScope
        println("global scope start")
        GlobalScope.launch {
            delay(5000)
            println("global scope")
        }
        println("global scope end")
        //  println("global scope start") yazacaktır sonra bu println("global scope end") sonra println("global scope start") yazıldıktan 5sn sonra  println("global scope") yazar

         */

        //****************************************

        /*
        //en fazla kullanıdır
        //CoroutineScope
        //Context yerne geçere bu arada Dispatchers defaults , io , main ...
        println("coroutines scope start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("coroutines scope")
        }
        println("coroutines scope end")
        //start end yazı yazar 5sn sonra        println("coroutines scope") yazdırır

         */

        //****************************************

        //globalScope ile Coroutines Scope farkı globade heryerde çalıştırabiliriz ama coroutinete seçim önceliği var (thread)

        /*
        //CoroutineScope farklı şekildede çalıştırabilir
        println("run blocking start")
        runBlocking {
            launch {
                delay(5000)
                println("run blocking")
            }
            println("coroutine scope start")
            coroutineScope {
                launch {
                    delay(3000)
                    println("coroutine scope")
                }
            }
            println("coroutine scope end")
        }
        println("run blocking end")
        //run blocking start ve corotines scope start yazdırdı 3sn sonra  coroutine scope yazdırdı ve ardından   coroutine scope end yazdırdı sonra 2sn sonra run blocking yazdırdır sonra run blocking end yazdırdı

         */

        //****************************************

        /*
        //Context detayı
        //farklı threadlerle çalıştırmamıza imkan sağlıyor
        //Dispatchers.Default - > CPU Intensive yoğun işlerde kullanılır .mesela görsel işleme , çok uzun ibr diziyi dizme alfabetik vs.
        //Dispatchers.IO - > Input / Output , giriş çıkış Nertworking işlemleri, internete girip veri çekmek
        //Dispatchers.Main - > UI - kullanıcı arayüzünde
        //Dispatchers.Unconfined - > Inherited Dispatcher diyebiliriz
        //dafaultta yaptınız bir işlemi mesela mainde gösterebilirsiniz

        runBlocking {
            //launch() dispatcher girilebilir alsında
            launch(Dispatchers.Main) {
                println("Main Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                println("IO Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                println("Default Thread: ${Thread.currentThread().name}")
            }

            launch(Dispatchers.Unconfined) {
                println("Unconfined Thread: ${Thread.currentThread().name}")
            }
        }
         */

        //****************************************

        /*
        //Suspend Coroutine - içersinde coroutines çalıştırabilen fonksiyonalrdır. bu fonsiyonlar suspend edebilir istediğimiz durdurup devam ettirebiliriz
        //suspend coroutines scope içinde çağırılabilir sadece
//        GlobalScope.launch {
//            myFunction()
//        }
        println("run blocking start")
        runBlocking {
            delay(2000)
            println("run blocking")
            myFunction()
        }
        //start çalıştı 1sn sonra blocking çalıştı 4sn sonra myFuncction çalıştıs

         */

        //****************************************

        /*
        //coroutines async
        //amaç verileri geldiğinde alabilmek
        var userName = ""
        var userAge = 0
        runBlocking {
//            launch {
//                val downloadedName = downloadName()
//                userName = downloadedName
//            }
//            launch {
//                val downloadedAge = downloadAge()
//                userAge = downloadedAge
//            }
//            println("${userName} ${userAge}")
//            //aynı zamanda olmadığı için veriler sonra geliyor vs...

            val downloadedName=async { downloadName() }
            val downloadedAge=async { downloadAge() }
            //await() demek üstteki async un bitmesini beklemesi demek bitince veriyi atar
            userName=downloadedName.await()
            userAge=downloadedAge.await()
            //amacı zaten bu sonunda farklı zamanda çekse dahi verilerin gelmesi
        }
         */

        //****************************************

        /*
        //jobCoroutines
        //job- kontrol edilebilir
        runBlocking {
            val myJob=launch {
                delay(2000)
                println("Job")
                val secondJob=launch {
                    println("Job 2")
                }
            }
            //işim tamamlandığında yapılcaklar
            myJob.invokeOnCompletion {
                println("myJob end ")
            }
            //1sn sonra job yazdırır sonra job2 sonra end

            //işi iptal eder
            //genelde coroutinesleri kapatmak için kullanılır fragmentler kullanılır çıkış yaparken
            //myJob.cancel()
            //my job end yazdırır
        }
         */

        //****************************************

        //withContext
        runBlocking {
            launch(Dispatchers.Default) {
                //coroutineContext olan corouines contexti getirir
                println("Context: ${coroutineContext}")
                //withContext bir launch  içinden  dispatchersin diğerine referans verebiliriz
                withContext(Dispatchers.IO){
                    println("Context: ${coroutineContext}")
                }
            }
        }



    }

    suspend fun downloadName(): String {
        delay(2000)
        var userName = "Atıl";
        println("userName Download")
        return userName
    }

    suspend fun downloadAge(): Int {
        delay(4000)
        var userAge = 60;
        println("userAge Download")
        return userAge
    }

    suspend fun myFunction() {
        coroutineScope {
            delay(4000)
            println("suspend function")
        }
    }
}