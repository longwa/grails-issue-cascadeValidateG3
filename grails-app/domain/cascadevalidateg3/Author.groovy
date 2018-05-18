package cascadevalidateg3

class Author {
    String name
    Publisher publisher

    boolean validateCalled = false
    static transients = ['validateCalled']

    static constraints = {
        name(nullable: false, validator: { val, obj ->
            println "validator called for Author.name"
            obj.validateCalled = true
        })
    }
}
