package io.micronaut.starter.generator.app

import io.micronaut.starter.application.ApplicationType
import io.micronaut.starter.generator.CommandSpec
import io.micronaut.starter.options.BuildTool
import io.micronaut.starter.options.Language
import spock.lang.Unroll

class CreateAppGroovyMavenSpec extends CommandSpec {

    @Unroll
    void 'test basic create-app for #lang and #buildTool'(ApplicationType applicationType,
                                                      Language lang,
                                                      BuildTool buildTool) {
        given:
        generateProject(lang, buildTool, ['random-port'])

        when:
        if (buildTool == BuildTool.GRADLE) {
            executeGradleCommand('run')
        } else {
            executeMavenCommand("mn:run")
        }

        then:
        testOutputContains("Startup completed")

        where:
        applicationType         | lang                    | buildTool
        ApplicationType.DEFAULT | Language.GROOVY         | BuildTool.MAVEN
    }

    @Override
    String getTempDirectoryPrefix() {
        return "starter-core-test-app-createappgroovymavenspec"
    }
}