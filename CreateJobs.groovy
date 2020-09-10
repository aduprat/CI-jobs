def repositories = [
  'esn-frontend-admin'
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

listView('admin') {
  jobs {
    names(repositories.toArray(new String[repositories.size()]))
  }
  columns {
    status()
    weather()
    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
}
