# Write Tests, Get Types!

A demo for Typed Clojure's runtime type inference.

## Overview

Run your tests, and `clojure.core.typed/runtime-infer`
will insert your annotations.

## Quick start

1. Add these injections to project.clj

   ```clojure
     :injections [(require 'clojure.core.typed)
                  (clojure.core.typed/install
                    #{:load})])
   ```

2. Add {:lang :core.typed} metadata to your ns form.

   ```clojure
   (ns my-ns
     {:lang :core.typed}
     (:require [clojure.repl :as r]))
   ```

3. Run your tests from the REPL.

4. Use `clojure.core.typed/runtime-infer` to generate
   core.typed types in the current namespace.

5. Use `clojure.core.typed/spec-infer` to generate
   core.typed types in the current namespace.

6. Use `clojure.core.typed/refresh-runtime-infer` to
   wipe inference results (perhaps from running bad tests).

## Verbose

What follows:
- 3 examples of inferring types
- instructions on how to try this on your own code

## Example 1

Let's infer the annotations in `runtime-infer-demo.core`.

First, we run its unit tests.

```clojure
user=> (require 'runtime-infer-demo.core-test)
nil
user=> (in-ns 'runtime-infer-demo.core-test)
#object[clojure.lang.Namespace 0x193501b1 "runtime-infer-demo.core-test"]
runtime-infer-demo.core-test=> (run-tests)

Testing runtime-infer-demo.core-test

Ran 1 tests containing 2 assertions.
0 failures, 0 errors.
{:test 1, :pass 2, :fail 0, :error 0, :type :summary}
```

Then we use the `(runtime-infer)` function call to
insert annotations directly in the file.

```clojure
runtime-infer-demo.core-test=> (in-ns 'runtime-infer-demo.core)
#object[clojure.lang.Namespace 0x17e656a9 "runtime-infer-demo.core"]
runtime-infer-demo.core=> (t/runtime-infer)
nil
```

We can then type check this file by changing the ns form to:

```clojure
(ns runtime-infer-demo.core
  {:lang :core.typed}
  (:require [clojure.core.typed :as t]))
```

and simply reloading the file in our REPL:

```clojure
runtime-infer-demo.core=> (require 'runtime-infer-demo.core :reload)
nil
```

It's really type checking the code, try and invoke a null-pointer
with `(inc nil)` (by inserting and saving the file) and notice we get a compile-time error!

```clojure
runtime-infer-demo.core=> (require 'runtime-infer-demo.core :reload)
Type Error (runtime_infer_demo/core.clj:23:1) Static method clojure.lang.Numbers/inc could not be applied to arguments:


Domains:
  Number

Arguments:
  nil

Ranges:
  Number


in: (clojure.lang.Numbers/inc nil)



ExceptionInfo Type Checker: Found 1 error  clojure.core/ex-info (core.clj:4724)
```

## Example 2

```clojure
user=> (require 'runtime-infer-demo.server-port-test)
nil
user=> (in-ns 'runtime-infer-demo.server-port-test)
#object[clojure.lang.Namespace 0x225b5fc9 "runtime-infer-demo.server-port-test"]
runtime-infer-demo.server-port-test=> (run-tests)

Testing runtime-infer-demo.server-port-test

Ran 1 tests containing 1 assertions.
0 failures, 0 errors.
{:test 1, :pass 1, :fail 0, :error 0, :type :summary}
runtime-infer-demo.server-port-test=> (in-ns 'runtime-infer-demo.server-port)
#object[clojure.lang.Namespace 0x138e38b6 "runtime-infer-demo.server-port"]
runtime-infer-demo.server-port=> (t/runtime-infer)
nil
```

Now change the ns form to:

```clojure
(ns runtime-infer-demo.server-port
  {:lang :core.typed}
  (:require [clojure.core.typed :as t]))
```

It should type check:

```
runtime-infer-demo.server-port=> (require 'runtime-infer-demo.server-port :reload)
nil
```

## Example 3

```clojure
runtime-infer-demo.server-port=> (require 'runtime-infer-demo.mini-occ)
WARNING: Removing :no-check from var clojure.pprint/pprint
WARNING: Duplicate var annotation:  clojure.pprint/pprint
Instrumenting clojure.pprint/pprint in runtime-infer-demo.mini-occ
nil
runtime-infer-demo.server-port=> (in-ns 'runtime-infer-demo.mini-occ)
#object[clojure.lang.Namespace 0x5f2b44ec "runtime-infer-demo.mini-occ"]
runtime-infer-demo.mini-occ=> (clojure.test/run-
clojure.test/run-all-tests   
clojure.test/run-tests       
runtime-infer-demo.mini-occ=> (clojure.test/run-tests)

Testing runtime-infer-demo.mini-occ
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo
foo

Ran 5 tests containing 19 assertions.
0 failures, 0 errors.
{:test 5, :pass 19, :fail 0, :error 0, :type :summary}
```

Now generate the types:

```clojure
runtime-infer-demo.mini-occ=> (t/runtime-infer)
nil
```

Check out `runtime-infer-demo.mini-occ` for the 
output. These won't type check as-is, but they
could be used to guide the final annotations
(right now they're pretty ugly).

## Usage

See the `project.clj` for the needed configuration.
The code in `:injections` must be added before
your code is run (it monkey patches `load`).

To infer types for your namespace, add this metadata
to your namespace (like in runtime-infer-demo.core):

```clojure
(ns runtime-infer-demo.core
  {:lang :core.typed
   :core.typed {:features #{:runtime-infer}}}
  (:require [clojure.core.typed :as t]))
```

Now run your unit tests, and call `(t/runtime-infer)`
in your namespace.
Allow your editor to reload your file (eg. in `vim`
use the `:edit` command), and notice your files will
have annotations.

## License

Copyright © 2016 Ambrose Bonnaire-Sergeant

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
