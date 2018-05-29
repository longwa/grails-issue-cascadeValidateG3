package cascadevalidateg3

class Publisher {
    String name

    boolean validateCalled = false
    boolean beforeValidateCalled = false
    static transients = ['validateCalled', 'beforeValidateCalled']

    static constraints = {
        name(nullable: false, validator: { val, obj ->
            println "validator called for Publisher.name"
            obj.validateCalled = true
        })
    }

    def beforeValidate() {
        println "beforeValidate called on Publisher"
        beforeValidateCalled = true
    }
}
