def repositories = [
  'esn-frontend-admin',
  'esn-frontend-inbox'
]

repositories.each {
  def repo = it

  multibranchPipelineJob(repo) {
    branchSources {
      github {
        id(repo)
        scanCredentialsId('github')
        repoOwner('aduprat')
        repository(repo)
        buildOriginPRHead(true)
        buildOriginBranchWithPR(false)
      }
    }
    triggers {
      periodicFolderTrigger {
        interval('10')
      }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
  }
}
