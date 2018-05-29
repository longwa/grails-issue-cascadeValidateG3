package cascadevalidateg3

class Book {
    String name

    static belongsTo = [author: Author]

    boolean validateCalled = false
    static transients = ['validateCalled']

    static constraints = {
        name(nullable: false, validator: { val, obj ->
            println "validator called for Book.name"
            obj.validateCalled = true
        })
    }

    def beforeValidate() {
        println "beforeValidate called on Book"
    }
}
