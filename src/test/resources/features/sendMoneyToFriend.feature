Feature: Send money to a friend
  This feature describes various scenarios for users sending the money to a friend

  #As a user, I want to send my friend the money

  Scenario: Send money to a friend
    Given Danny has 100 "EUR" in his account
    And Catriona has 100 "EUR" on her account
    When Danny wish to send 50 "EUR" to Catriona
    Then Accounts balance in "EUR" should be updated as Catriona 150, Danny 50

  Scenario: Send money in different currency
    Given Danny opens a new "GBP" account and deposited 50
    And Catriona has 100 "EUR" on her account
    When Danny wish to send 50 "GBP" to Catriona
    Then Accounts balance in "GBP" should be updated as Catriona 50, Danny 0

  Rule: Series of tests to check send to friend feature is working as expected
    Scenario Outline: On some cases send to friend should be rejected due to insufficient funds on sender account
      Given Danny has <dannyStartBalance> <currency> in his account
      And Catriona has <catrionaStartBalance> <currency> on her account
      When Danny wish to send <sendAmount> <currency> to Catriona
      Then Accounts balance in <currency> should be updated as Catriona <catrionaBalance>, Danny <dannyBalance>
      Then Number of currency accounts should be <noAccounts>
      Examples:
        | dannyStartBalance | catrionaStartBalance  | currency | sendAmount | catrionaBalance | dannyBalance | noAccounts |
        | 100.00            | 150                   | "GBP"    | 100.01     | 150             | 100          | 2          |
        | 1000              | 200                   | "EUR"    | 500        | 700             | 500          | 1          |
        | 3000              | 400                   | "JPY"    | 100        | 500             | 2900         | 2          |
        | 100               | 100                   | "EUR"    | 100        | 200             | 0            | 1          |