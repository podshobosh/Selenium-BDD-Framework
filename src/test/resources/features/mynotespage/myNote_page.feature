Feature: Verifying My Notes Tab fields


  @UI @TestCase1
  Scenario: Verify My Notes Tab fields
    Given User click on My Notes Tab
    And Validate that topic of fields are present at the left side of the page
    When User clicks on Selenium topic
    Then Validate Selenium title is present

  @UI @Testcase2
  Scenario Outline: Validate each section title after clicking sidebar links
    Given User click on My Notes Tab
    When User clicks the "<section>" link from the sidebar
    Then User should see title "<section>"

    Examples:
      | section      |
      | Selenium     |
      | Java         |
      | SQL          |
      | Cucumber     |
      | REST Assured |
      | Git          |
      | Cypress      |
      | TestNG       |
      | Jenkins      |

  @UI @TestCase3
  Scenario: Validate the functionality of the hide category button
    Given User click on My Notes Tab
    When user clicks the Hide Category button
    Then categories tab should be hidden
