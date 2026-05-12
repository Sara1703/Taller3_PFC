package object Newton {

    trait Expr
        case class Numero(d: Double) extends Expr

        case class Atomo(x: Char) extends Expr

        case class Suma(e1: Expr, e2: Expr) extends Expr

        case class Prod(e1: Expr, e2: Expr) extends Expr

        case class Resta(e1: Expr, e2: Expr) extends Expr

        case class Div(e1: Expr, e2: Expr) extends Expr

        case class Expo(e1: Expr, e2: Expr) extends Expr

        case class Logaritmo(e1: Expr) extends Expr

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

    def evaluar ( f:Expr , a :Atomo , v:Double ) : Double =  f match{
        case  Numero(d) => d
        case  Atomo(x) => v
        case Suma(e1,e2) => evaluar(e1,a,v) + evaluar(e2,a, v)
        case Resta(e1,e2) => evaluar(e1,a,v) - evaluar(e2,a, v)
        case Prod(e1,e2) => evaluar(e1,a,v) * evaluar(e2,a, v)
        case Div(e1,e2) => evaluar(e1,a,v) / evaluar(e2,a, v)
        case Expo(e1,e2) => math.pow(evaluar(e1,a,v), evaluar(e2,a, v))
        case Logaritmo(e1) =>  Math.log(evaluar(e1, a, v))
    }



    def  limpiar (f: Expr): Expr  = f match {
        case Suma(Numero(0.0), e)  => limpiar(e)
        case Suma(e, Numero(0.0))  => limpiar(e)
        case Resta(e, Numero(0.0)) => limpiar(e)
        case Prod(Numero(0.0), _)  => Numero(0.0)
        case Prod(_, Numero(0.0))  => Numero(0.0)
        case Prod(Numero(1.0), e)  => limpiar(e)
        case Prod(e, Numero(1.0))  => limpiar(e)
        case Div(e, Numero(1.0))   => limpiar(e)
        case Expo(_, Numero(0.0))  => Numero(1.0)
        case Expo(e, Numero(1.0))  => limpiar(e)
        case Suma(e1, e2)          => limpiar(Suma(limpiar(e1), limpiar(e2)))
        case Resta(e1, e2)         => limpiar(Resta(limpiar(e1), limpiar(e2)))
        case Prod(e1, e2)          => limpiar(Prod(limpiar(e1), limpiar(e2)))
        case Div(e1, e2)           => limpiar(Div(limpiar(e1), limpiar(e2)))
        case Expo(e1, e2)          => limpiar(Expo(limpiar(e1), limpiar(e2)))
        case Logaritmo(e1)         => Logaritmo(limpiar(e1))
        case other                 => other
    }


    def raizNewton(f: Expr, a: Atomo, x0: Double,
                   ba: (Expr, Atomo, Double) => Boolean): Double = {
        if (ba(f, a, x0)) {
            x0
        } else {
            val df = derivar(f, a)
            val x1 = x0 - evaluar(f, a, x0) / evaluar(df, a, x0)
            raizNewton(f, a, x1, ba)
        }
    }
}