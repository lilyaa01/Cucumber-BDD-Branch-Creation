Feature: Create GitHub repository

  As a GitHub user
  I want to create a new repository
  So that I can store my code

  Scenario: Successfully create a public repository
    Given I am logged in to Github
    When I navigate to the "Create a new repository" page
    And I fill in the repository name with "Test-repo-7"
    And I select "Public" as the repository visibility
    And I click the "Create repository" button
    Then I should be redirected to the page of the repository with name "Test-repo-7"
    And the title of the page should be "Test-repo-7"
    #And the visibility label should be "Public"
