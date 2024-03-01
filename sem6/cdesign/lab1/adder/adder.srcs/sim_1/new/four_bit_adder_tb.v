`timescale 1ns / 1ps


module four_bit_adder_tb;

reg [3:0] a_in, b_in;
wire [3:0] s_out;
wire c_out;

four_bit_adder adder1(
    .a_arr(a_in),
    .b_arr(b_in),
    .s_arr(s_out),
    .c(c_out)
);

integer i, j;
reg [3:0] expected_val;
reg expected_carry;

initial begin
    for (i = 0; i < 16; i = i+1) begin
        for (j = 0; j < 16; j = j+1) begin
            a_in = i;
            b_in = j;
            
            {expected_carry, expected_val} = i + j;

            #10
            
            if (s_out == expected_val && c_out == expected_carry) begin
                $display("The adder output is correct: a_in=%b, b_in=%b, s_out=%b, c_out=%b",
                    a_in, b_in, s_out, c_out);
            end else begin
                $display("The adder output is wrong: a_in=%b, b_in=%b, s_out=%b, c_out=%b, expected_val=%b, expected_carry=%b",
                    a_in, b_in, s_out, c_out, expected_val, expected_carry);
            end
        end
    end
    #10 $stop;
end

endmodule
