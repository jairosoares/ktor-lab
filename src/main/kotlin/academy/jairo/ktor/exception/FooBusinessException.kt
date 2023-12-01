package academy.jairo.ktor.exception

class FooBusinessException (param: String) :
    BusinessException(MessageCode.BUSINESS_LOGIC_FOO, "Foo param $param error logic!")