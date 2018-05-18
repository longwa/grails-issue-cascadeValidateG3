package cascadevalidateg3

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
class AuthorSpec extends Specification {
    void "validate should not cascade to associated entity proxy that is not initialized"() {
        def id = Author.withNewSession {
            Publisher publisher = new Publisher(name: 'Brad').save()
            new Author(name: 'Aaron', publisher: publisher).save(flush: true, failOnError: true).id
        }

        when:
        Author author = Author.get(id)
        author.name = "Joe"
        author.save(flush: true, failOnError: true)

        then:
        author.validateCalled
        !author.publisher.validateCalled
    }

    void "validate should not cascade to associated entity that is not dirty and not owned"() {
        def id = Author.withNewSession {
            Publisher publisher = new Publisher(name: 'Brad').save()
            new Author(name: 'Aaron', publisher: publisher).save(flush: true, failOnError: true).id
        }

        when:
        Author author = Author.get(id)
        author.name = "Joe"
        println "Ensure publisher proxy is unwrapped: Publisher name - ${author.publisher.name}"
        author.save(flush: true, failOnError: true)

        then:
        author.validateCalled
        !author.publisher.validateCalled
    }

    void "validate should cascade to associated entity that is dirty"() {
        def id = Author.withNewSession {
            Publisher publisher = new Publisher(name: 'Brad').save()
            new Author(name: 'Aaron', publisher: publisher).save(flush: true, failOnError: true).id
        }

        when:
        Author author = Author.get(id)
        author.name = "Joe"
        author.publisher.name = "Jim"
        author.save(flush: true, failOnError: true)

        then:
        author.validateCalled
        author.publisher.validateCalled
    }
}