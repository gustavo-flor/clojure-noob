# Clojure Noob

A simple project to put Clojure concepts into practice.

## Dependencies

- Java
- Leiningen

## Usage

Execute REPL (Read-Eval-Print Loop)

```sh
$ lein repl
```

You should see something like this:

```text
nREPL server started on port #### on host 127.0.0.1
REPL-y 0.5.1, nREPL 0.8.3
Clojure 1.10.3
Java HotSpot(TM) 64-Bit Server VM 17.0.1+12-LTS-39
Docs: (doc function-name-here)
      (find-doc "part-of-name-here")
Source: (source function-name-here)
Javadoc: (javadoc java-object-or-class-here)
Exit: Control+D or (exit) or (quit)
Results: Stored in vars *1, *2, *3, an exception in *e
```

Now inside REPL we'll execute the main class with:

```clojure
(-main)
```

Also, we can execute

```clojure
(+ 1 2 3 4)
;; 10

(* 1 2 3 4)
;; 24

(first [3, 7, 1, 2])
;; 3

(do (println "Hello World")
    (- 10 5 2))
;; Hello World
;; 3
```

> **💫 Happy Hacking!**


