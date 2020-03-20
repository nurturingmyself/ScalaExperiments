package com.madhukaraphatak.scala.testcontainers

import java.sql.DriverManager

import com.dimafeng.testcontainers.{ForAllTestContainer, MySQLContainer}
import org.scalatest.{FlatSpec, Matchers}

class MysqlTestSpec extends FlatSpec with ForAllTestContainer  with Matchers{
  override val container = MySQLContainer()

  it should "create table and list Table" in {

    Class.forName(container.driverClassName)
    val connection = DriverManager.getConnection(container.jdbcUrl,
      container.username, container.password)

    val createTableStatement = connection.prepareStatement("create table test(a  Int)")
    createTableStatement.execute()

    val preparedStatement = connection.prepareStatement("show tables")
    val result = preparedStatement.executeQuery()

    while (result.next()) {

      val tableName = result.getString(1)
      tableName shouldEqual "test"
    }
  }

    it should "Checks the table exist as test cases are sharing same container" in {

      Class.forName(container.driverClassName)
      val connection = DriverManager.getConnection(container.jdbcUrl,
        container.username, container.password)

      val preparedStatement = connection.prepareStatement("show tables")
      val result = preparedStatement.executeQuery()

      while (result.next()) {
        val tableName = result.getString(1)
        tableName shouldEqual "test"
      }


  }

}
