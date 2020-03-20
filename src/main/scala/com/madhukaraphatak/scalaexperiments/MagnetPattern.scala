package com.madhukaraphatak.scalaexperiments

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object MagnetPattern {



  sealed trait CompletionMagnet {
    type Result
    def apply(): Result
  }

  def complete(magnet:CompletionMagnet):magnet.Result = magnet()

  object CompletionMagnet {
    implicit def fromNumber(number:Int) = {
      new CompletionMagnet {
        override type Result = Int

        override def apply(): Result = number
      }
    }

     implicit def fromNumberAndMessage(tuple: (Int,String)) = {
      new CompletionMagnet {
        override type Result = String

        override def apply(): Result = tuple._2+"  "+tuple._1
      }
    }



  }

  /*def complete[T:Int](value:Int, obj:T)={

  }*/

  /*def completeFuture(value:Future[String]):String = Await.result(value,Duration.Zero)

  def completeFuture(value: Future[Int]):Int =  Await.result(value,Duration.Zero)*/

  sealed trait FutureMagnet {
    type Result

    def apply() : Result
  }

  def completeFuture(magnet: FutureMagnet):magnet.Result = magnet()

  object FutureMagnet {
    implicit def intFutureCompleter(future:Future[Int]) = new FutureMagnet {
      override type Result = Int

      override def apply(): Result = Await.result(future,Duration.Zero)
    }
    implicit def stringFutureCompleter(future:Future[String]) = new FutureMagnet {
      override type Result = String

      override def apply(): Result = Await.result(future,Duration.Zero)
    }

  }


  def main(args: Array[String]): Unit = {

    println(complete(10))
    println(complete(10,"hello"))

    println(completeFuture(Future{
      1}))

    println(completeFuture(Future{
      "hello future"}))



  }

}
