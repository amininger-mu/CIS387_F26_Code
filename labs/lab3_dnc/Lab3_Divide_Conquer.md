# Lab 3 Activity - Divide and Conquer

## Part 1: RSA

Note: Look online for a list of primes, choose primes _p, q, e_ that are > 100

### 1. Write primes _p_ and _q_ (> 100): 

<br>

### 2. Compute _n_ = p*q and _phi_ = (p-1)(q-1)

<br>

### 3. Choose a prime _e_ < phi that is coprime to phi
Make sure that `phi % e != 0`

**Write Public Key (n, e):**

<br>

### 4. Use the `ModInv` java program to compute the private key
Compute _d_ such that (e*d mod phi == 1)

**Private Key (n, d):**

<br>

### 5. Give your public key to another student

### 6. Choose a message _m_ (an int < n)

<br>

### 7. Encrypt your message with another student's public key
Use `ModExp` program to compute $c = m^e \mod n$

<br>

### 8. Give the encrypted message back to the original student

<br>

### 9. Decrypt the message you received with your private key
Use `ModExp` program to compute $c^d \mod n$

<br>

### 10. Write the answer here. Verify with the original student

<!-- pb -->
