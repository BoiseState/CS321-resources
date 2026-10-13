
# Demo of Universal Random hashing

`UniversalHashFamily.java`:  Provides a simple class to generate a family of universal hash functions.
The class allows you to create hash functions that can be used for various applications. Note that
this implementation isn't fully secure and should not be used for cryptographic purposes. 

`CryptographicallySecureHash.java`: Provides a simple class to generate a family of cryptographically
secure hash functions. This is more secure than the previous example while retaining the same
interface. This implementation is suitable for applications that require a higher level of security.

`AttackDemo.java`: Show how an attacker can exploit the weaknesses of hash functions to find
collisions. This class demonstrates how an attacker can generate inputs that produce the same
hash output, highlighting the importance of using universal and cryptographically secure hash
functions in security-sensitive applications.

`TestRandomHashing.java`: Contains a demo of using the `UniversalHashFamily` and
`CryptographicallySecureHash` classes.

