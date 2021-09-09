Feature: Send money to a friend
  This feature describes various scenarios for users sending the money to a friend

  #As a user, I want to send my friend a money

  Scenario: Send money to a friend
    Given Danny has 100 euro in his euro account
    And Catriona has 100 euro on her account
    When Danny wish to send 50 "EUR" to Catriona
    Then Accounts in "EUR" the balances should be Catriona 150, Danny 50

  Scenario: Send money in different currency
    Given Danny opens a new "GBP" account and deposit 50
    And Catriona has 100 euro on her account
    When Danny wish to send 50 "GBP" to Catriona
    Then Accounts in "GBP" the balances should be Catriona 50, Danny 0