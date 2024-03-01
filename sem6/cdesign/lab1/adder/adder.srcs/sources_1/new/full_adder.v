`timescale 1ns / 1ps


module full_adder(
    input a,
    input b,
    input c_in,
    output s,
    output c_out
);

wire nor_ab, b_and_not_a, a_and_not_b, eq_ab;

wire nor_cin_eqab, cin_and_not_eqab, eqab_and_not_cin;

nor(nor_ab, a, b);
nor(b_and_not_a, a, nor_ab);
nor(a_and_not_b, nor_ab, b);
nor(eq_ab, b_and_not_a, a_and_not_b);

nor(nor_cin_eqab, eq_ab, c_in);
nor(cin_and_not_eqab, eq_ab, nor_cin_eqab);
nor(eqab_and_not_cin, c_in, nor_cin_eqab);
nor(s, cin_and_not_eqab, eqab_and_not_cin);

nor(c_out, nor_cin_eqab, nor_ab);

endmodule
