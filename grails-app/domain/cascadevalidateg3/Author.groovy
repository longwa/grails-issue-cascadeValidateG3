package cascadevalidateg3

class Author {
    String name
    Publisher publisher

    static hasMany = [books: Book]

    boolean validateCalled = false
    static transients = ['validateCalled']

    static constraints = {
        name(nullable: false, validator: { val, obj ->
            println "validator called for Author.name"
            obj.validateCalled = true
        })
        publisher(nullable: true)
    }

    static mapping = {
        publisher(cascadeValidate: false)
    }

    def beforeValidate() {
        println "beforeValidate called on Author"
    }
}
