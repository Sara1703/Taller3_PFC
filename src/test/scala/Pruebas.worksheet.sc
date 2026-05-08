import Newton ._

val expr1=Div(Atomo('x'), Numero(7))
val expr2= Suma(Numero(1), Atomo('x'))
val expr3 = Prod(expr2, Expo(expr2,Numero(3)))
val expr4= Logaritmo(expr3)
val expr5= Expo(Div(expr2,expr3), Resta(expr1,Numero(2)))
val expr6= Resta(Atomo('x'), Numero(3))

mostrar(expr1)
mostrar(expr2)
mostrar(expr3)
mostrar(expr4)
mostrar(expr5)
mostrar(expr6)

////

mostrar(derivar(Numero(3),Atomo('x')))
mostrar(derivar(expr6,Atomo('x')))
mostrar(derivar(expr1,Atomo('y')))
mostrar(derivar(Resta(Numero(6),Prod(Numero(3.0),Atomo('y'))),Atomo('x')))
mostrar(derivar(Div(Atomo('x'),Suma(Atomo('x'),Numero(5.0))),Atomo('k')))
mostrar(derivar(Numero(9),Atomo('x')))