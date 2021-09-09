Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 250



  #ToDo implement the remaining scenarios listed below

  #To implement this scenario you will need to use data tables
    # https://cucumber.io/docs/cucumber/api/
  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    And Selected payment service method has a limit of <topUpAmount>
    When Danny now tops up by <topUpAmount>
    Then His account should return amount of <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

    #The scenarios below will need a payment service that accepts or rejects a request to add funds
    Scenario Outline: Payment service rejects the request
      Given Danny has a starting balance of 0
      And Danny selects his DebitCard as his topUp method
      And Selected payment service method has a limit of <serviceLimit>
      When Danny now tops up by <topUpAmount>
      Then His account should return amount of 0
      Examples:
        | serviceLimit  | topUpAmount |
        | 299.99        | 300         |
        | 10            | 20          |
        | 40            | 40.01       |

    Scenario: Payment service accepts the request
      Given Danny has a starting balance of 0
      And Danny selects his DebitCard as his topUp method
      And Selected payment service method has a limit of 100
      When Danny now tops up by 100
      Then His account should return amount of 100