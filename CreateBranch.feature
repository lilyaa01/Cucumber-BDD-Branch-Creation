Feature: Create a new branch in a GitHub repository

  Background:
    Given the user is logged into GitHub
    And the user opens the repository "bdd-demo-repo"

  Scenario: Successfully create a new branch
    When the user opens the branch selector
    And the user creates a branch named "feature/test-branch"
    Then the branch "feature/test-branch" should appear in the branch list
