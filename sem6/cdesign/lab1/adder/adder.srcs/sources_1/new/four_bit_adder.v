`timescale 1ns / 1ps


module four_bit_adder(
    a_arr,
    b_arr,
    s_arr,
    c
);

input [3:0] a_arr;
input [3:0] b_arr;
output [3:0] s_arr;
output c;


wire c0, c1, c2;

full_adder adder0(
    .a(a_arr[0]),
    .b(b_arr[0]),
    .c_in('b0),
    .s(s_arr[0]),
    .c_out(c0)
);

full_adder adder1(
    .a(a_arr[1]),
    .b(b_arr[1]),
    .c_in(c0),
    .s(s_arr[1]),
    .c_out(c1)
);

full_adder adder2(
    .a(a_arr[2]),
    .b(b_arr[2]),
    .c_in(c1),
    .s(s_arr[2]),
    .c_out(c2)
);

full_adder adder3(
    .a(a_arr[3]),
    .b(b_arr[3]),
    .c_in(c2),
    .s(s_arr[3]),
    .c_out(c)
);

endmodule
