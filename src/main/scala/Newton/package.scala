package object Newton {

    trait Expr 
    case class Numero (d:Double) extends Expr
    case class Atomo (x:Char) extends Expr
    case class Suma (e1:Expr, e2:Expr) extends Expr
    case class Prod (e1:Expr, e2:Expr) extends Expr
    case class Resta (e1:Expr, e2:Expr) extends Expr
    case class Div (e1:Expr, e2:Expr) extends Expr
    case class Expo (e1:Expr, e2:Expr) extends Expr
    case class Logaritmo (e1:Expr) extends Expr

    def mostrar (e:Expr): String = e match {

        case Numero(d) => d.toString
        case Atomo(x) => x.toString
        case Suma(e1,e2) => "("+ mostrar(e1)+ " + " + mostrar(e2) +")"
        case Prod(e1,e2) => "("+ mostrar(e1)+ " * " + mostrar(e2) +")"
        case Resta(e1,e2) => "("+ mostrar(e1)+ " - " + mostrar(e2) +")"
        case Div(e1, e2) => "("+ mostrar(e1)+ " / " + mostrar(e2) +")"
        case Expo(e1, e2) => "("+ mostrar(e1)+ " ^ " + mostrar(e2) +")"
        case Logaritmo(e1) => "(lg(" + mostrar(e1) +  "))"
    
    }

    def derivar(f:Expr, a:Atomo): Expr = f match {

        case Numero(_) => Numero(0)
        case `a` => Numero(1)
        case Atomo(_) => Numero(0)

        case Suma(e1,e2) => Suma(derivar(e1,a), derivar(e2, a))
        case Resta(e1,e2) => Resta(derivar(e1,a), derivar(e2,a))
        case Prod(e1,e2) => Suma(Prod(derivar(e1,a),e2), Prod(e1,derivar(e2,a)))
        case Div(e1, e2) => Div(Resta(Prod(derivar(e1,a),e2), Prod(e1,derivar(e2,a))),Expo(e1,Numero(2)))
        case Logaritmo(e1) => Div(derivar(e1,a), e1)
        case Expo(e1,e2) => 
            Prod(Expo(e1,e2),
                Suma(
                    Div(Prod(derivar(e1,a),e2),e1),
                    Prod(derivar(e2,a), Logaritmo(e1))
                )
        )

    }
    
}