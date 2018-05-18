package cascadevalidateg3

class Publisher {
    String name

    boolean validateCalled = false
    static transients = ['validateCalled']

    static constraints = {
        name(nullable: false, validator: { val, obj ->
            println "validator called for Publisher.name"
            obj.validateCalled = true
        })
    }
}
