`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/09/2024 01:11:10 PM
// Design Name: 
// Module Name: sqrt_tb
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module sqrt_tb;

    reg clk = 0;
    reg rst;
    reg [7:0] x;
    reg start;

    wire busy;
    wire [7:0] y;
    
    sqrt sqrt(
        .x_bi(x),
        .start_i(start),
        .clk_i(clk),
        .rst_i(rst),
        
        .busy_o(busy),
        .y_bo(y)
    );
    
    initial begin
    
        assign x = 11;
        assign rst = 0;
        assign start = 1;   
 
        
    end   
    
    always 
        #5  clk =  ! clk; 
            

endmodule
