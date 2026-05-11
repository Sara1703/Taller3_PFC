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
mostrar(derivar(expr5,Atomo('x')))
mostrar(derivar(expr1,Atomo('y')))
mostrar(derivar(Resta(Numero(6),Prod(Numero(3.0),Atomo('y'))),Atomo('x')))
mostrar(derivar(Div(Atomo('x'),Suma(Atomo('x'),Numero(5.0))),Atomo('k')))


mostrar (Numero ( 5.0 ))
evaluar(Numero ( 10.0 ) , Atomo( 'y'  ) , 8.0 )

mostrar (Atomo( 'z' ) )
evaluar (Atomo( 'y' ) ,Atomo( 'y' ) , 9.0 )

mostrar (Suma( expr1 , expr2 ) )
evaluar (Suma( expr1 , expr2 ) ,Atomo( 'w' ) , 2.0 )

mostrar ( Prod ( expr1 , expr2 ) )
evaluar ( Prod ( expr1 , expr2 ) ,Atomo( 'r' ) , 9.5 )

mostrar ( Resta ( expr1 , expr2 ) )
evaluar ( Resta ( expr1 , expr2 ) ,Atomo( 'v' ) , 20.0 )

mostrar ( Div ( expr1 , expr2 ) )
evaluar ( Div ( expr1 , expr2 ) ,Atomo( 'x' ) , 1.0 )

mostrar (Expo ( expr1 , expr2 ) )
evaluar (Expo ( expr1 , expr2 ) ,Atomo( 'c' ) , 10.0 )

mostrar ( Logaritmo ( expr1 ) )
evaluar ( Logaritmo ( expr1 ) ,Atomo( 'x' ) , 5.0 )